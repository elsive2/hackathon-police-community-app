const Stomp = require("stompjs");
const SockJS = require("sockjs-client");

const SERVER_URL = "http://localhost:6206/ws";
const USER_ID = "123";
const ACTIVE_CONTACT_ID = "456";
const USER_NAME = "John Doe";
const CONTACT_NAME = "Jane Doe";

let stompClient;

const connect = () => {
    const socket = new SockJS(SERVER_URL);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
};

const onConnected = () => {
    console.log("Connected to WebSocket");

    stompClient.subscribe(`/user/${USER_ID}/queue/messages`, onMessageReceived);
};

const onMessageReceived = (message) => {
    const msg = JSON.parse(message.body);
    console.log("New message received:", msg);
};

const sendMessage = (msg) => {
    if (msg.trim() !== "") {
        const message = {
            senderId: USER_ID,
            recipientId: ACTIVE_CONTACT_ID,
            senderName: USER_NAME,
            recipientName: CONTACT_NAME,
            content: msg,
            timestamp: new Date().toISOString(),
        };

        stompClient.send("/app/chat", {}, JSON.stringify(message));
        console.log("Message sent:", message);
    }
};

const onError = (error) => {
    console.error("WebSocket connection error:", error);
};

connect();

// Пример отправки сообщения через 5 секунд после подключения
// setTimeout(() => {
//     sendMessage("Hello from Node.js!");
// }, 5000);
