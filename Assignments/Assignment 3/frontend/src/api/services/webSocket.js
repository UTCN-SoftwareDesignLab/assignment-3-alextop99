import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
    CheckIn(patientName, hour, doctorName) {
        if (this.stompClient && this.stompClient.connected) {
            const msg = { patientName: patientName,
                hour: hour,
                doctorName: doctorName,
            };
            this.stompClient.send("/app/checkin", JSON.stringify(msg), {});
        }
    },
    connect() {
        let connected = false;
        this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.connect(
            {"Access-Control-Allow-Origin": "*"},
            () => {
                connected = true;
            },
            error => {
                console.log(error);
                connected = false;
            }
        );
        return connected;
    },
    disconnect() {
        let connected = false;
        if (this.stompClient) {
            this.stompClient.disconnect();
        }
        connected = false;
        return connected;
    },
}