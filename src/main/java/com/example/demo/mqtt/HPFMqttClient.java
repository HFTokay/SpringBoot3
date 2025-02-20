package com.example.demo.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class HPFMqttClient {

    public static MqttClient mqttClient = null;
    public static MemoryPersistence memoryPersistence = null;
    public static MqttConnectOptions mqttConnectOptions = null;

    private static int QoS = 1;
    private static String ClientID = "ClientID:" + UUID.randomUUID().toString();
    private static String Host = "tcp://127.0.0.1:1883";//HPFMqttRecieveMessage.Host;
    private static String USERNAME = "admin";//HPFMqttRecieveMessage.USERNAME;
    private static String PASSWORD = "qwertyuiop@123456";//HPFMqttRecieveMessage.PASSWORD;

    //初始化链接 客户端链接到到mqtt服务端    注释：需要mqtt就初始化，不用就不想需要
    static {

        init("init:" + ClientID);
        //subTopic("testTopic2/1234");  //只负责发布消息  不做订阅
    }

    public static void init(String clientId) {
        //初始化连接设置对象
        mqttConnectOptions = new MqttConnectOptions();
        //初始化MqttClient
        if (null != mqttConnectOptions) {
            // true可以安全地使用内存持久性作为客户端断开连接时清除的所有状态
            mqttConnectOptions.setCleanSession(true);
            // 设置连接超时
            mqttConnectOptions.setConnectionTimeout(30);
            mqttConnectOptions.setUserName(USERNAME);
            mqttConnectOptions.setPassword(PASSWORD.toCharArray());

            // 设置持久化方式
            memoryPersistence = new MemoryPersistence();
            if (null != memoryPersistence && null != clientId) {
                try {
                    mqttClient = new MqttClient(Host, clientId, memoryPersistence);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("MqttClient 创建失败");
            }
        } else {
            System.out.println("mqttConnectOptions对象为空");
        }

        System.out.println(mqttClient.isConnected());
        //设置连接和回调
        if (null != mqttClient) {
            if (!mqttClient.isConnected()) {
                // 创建回调函数对象
                MqttRecieveCallback mqttReceriveCallback = new MqttRecieveCallback();
                // 客户端添加回调函数
                mqttClient.setCallback(mqttReceriveCallback);
                try {
                    System.out.println("创建连接");
                    mqttClient.connect(mqttConnectOptions);
                } catch (MqttException e) {
                    e.printStackTrace();
                }

            }
        } else {
            System.out.println("mqttClient为空");
        }
        System.out.println(mqttClient.isConnected());
    }

    // 关闭连接
    public void closeConnect() {
        //关闭存储方式
        if (null != memoryPersistence) {
            try {
                memoryPersistence.close();
            } catch (MqttPersistenceException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("memoryPersistence is null");
        }

        // 关闭连接
        if (null != mqttClient) {
            if (mqttClient.isConnected()) {
                try {
                    mqttClient.disconnect();
                    mqttClient.close();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("mqttClient is not connect");
            }
        } else {
            System.out.println("mqttClient is null");
        }
    }

    // 发布消息
    public void publishMessage(String pubTopic, String message) {
        if (null != mqttClient && mqttClient.isConnected()) {
            System.out.println("-------------------------------------");
            System.out.println("发布消息是否成功： " + mqttClient.isConnected());
            System.out.println("clientId:" + mqttClient.getClientId());
            System.out.println("topic:" + pubTopic);
            System.out.println("message:" + message);

            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(QoS);
            mqttMessage.setPayload(message.getBytes());

            MqttTopic topic = mqttClient.getTopic(pubTopic);

            if (null != topic) {
                try {
                    MqttDeliveryToken publish = topic.publish(mqttMessage);
                    if (!publish.isComplete()) {
                        System.out.println("消息发布成功");
                    }
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            reConnect();
        }

    }

    // 重新连接
    public void reConnect() {
        if (null != mqttClient) {
            if (!mqttClient.isConnected()) {
                if (null != mqttConnectOptions) {
                    try {
                        mqttClient.connect(mqttConnectOptions);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("mqttConnectOptions is null");
                }
            } else {
                System.out.println("mqttClient is null or connect");
            }
        } else {
            //重连
            init(ClientID);
        }

    }

    // 订阅主题
    public static void subTopic(String topic) {
        if (null != mqttClient && mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topic, QoS);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("mqttClient is error");
        }
    }

    // 清空主题
    public void cleanTopic(String topic) {
        if (null != mqttClient && !mqttClient.isConnected()) {
            try {
                mqttClient.unsubscribe(topic);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("mqttClient is error");
        }
    }

}

