package com.example.demo.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


@Configuration
public class HPFMqttRecieveMessage {

    public static int QoS = 1;
    public static String Host = "tcp://127.0.0.1:1883";
    public static String ClientID ="ClientID:"+ UUID.randomUUID().toString();
    public static String USERNAME = "admin";
    public static String PASSWORD = "qwertyuiop@123456";

    private static MemoryPersistence memoryPersistence = null;
    private static MqttConnectOptions mqttConnectOptions = null;
    public static MqttClient mqttClient = null;

    public static void init(String clientId) {
        mqttConnectOptions = HPFMqttClient.mqttConnectOptions;
        memoryPersistence = HPFMqttClient.memoryPersistence;
        if (null != memoryPersistence && null != clientId && null != Host) {
            try {
                mqttClient = new MqttClient(Host, clientId, memoryPersistence);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("memoryPersistence clientId Host 有空值");
        }

        if (null != mqttConnectOptions) {
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(30);
            mqttConnectOptions.setKeepAliveInterval(45);
            mqttConnectOptions.setUserName(USERNAME);
            mqttConnectOptions.setPassword(PASSWORD.toCharArray());
            if (null != mqttClient && !mqttClient.isConnected()) {
                mqttClient.setCallback(new MqttRecieveCallback());
                try {
                    mqttClient.connect();
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                System.out.println("mqttClient is error");
            }
        } else {
            System.out.println("mqttConnectOptions is null");
        }
    }


    public static void recieve(String topic) {
        int[] Qos = {QoS};
        String[] topics = {topic};
        if (null != mqttClient && mqttClient.isConnected()) {
            if (null != topics && null != Qos && topics.length > 0 && Qos.length > 0) {
                try {
                    mqttClient.subscribe(topics, Qos);
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                System.out.println("mqttClient is error");
            }

        } else {
            init(ClientID);
            recieve(topic);
        }
    }

}
