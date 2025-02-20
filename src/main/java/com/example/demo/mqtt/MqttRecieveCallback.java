package com.example.demo.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttRecieveCallback implements MqttCallback {


    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("---------------------------------------------");
        System.out.println("Client 接受消息 自增 mqttMessageId: " + mqttMessage.getId());
        System.out.println("Client 接收消息主题 : " + topic);
        System.out.println("Client 接收消息内容 : " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
