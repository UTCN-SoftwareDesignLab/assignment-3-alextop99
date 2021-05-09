<template>
  <v-container>
    <Header/>
    <v-layout row justify-center pt-3>
      <v-flex xs40 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Doctor panel</h4>
            </v-card-title>
          </v-card>
          <v-card>
            <ConsultationsViewTable :consultations="consultations"></ConsultationsViewTable>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "@/api";
import Header from "@/components/Header";
import ConsultationsViewTable from "@/components/ConsultationsViewTable";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "Doctor",
  components: {ConsultationsViewTable, Header},
  data() {
    return {
      consultations: [],
    };
  },
  methods: {
    connectAndSubscribe() {
      this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
          {"Access-Control-Allow-Origin": "*"},
          () => {
            this.connected = true;
            this.stompClient.subscribe("/doctor/checkin", tick => {
              let response = JSON.parse(tick.body);
              if(response.doctorName === this.$store.state.auth.user.username) {
                this.$alert(response.message);
              }
            }, {"Access-Control-Allow-Origin": "*"});
          },
          error => {
            console.log(error);
            this.connected = false;
          }
      );
    },
  },
  async created() {
    let date = new Date();
    let dateString = date.getFullYear() + "-" + (date.getMonth() + 1 < 10 ? "0"+ (date.getMonth() + 1) : (date.getMonth() + 1)) + "-" + (date.getDate() < 10 ? "0"+ date.getDate() : date.getDate());
    this.consultations = await api.consultations.getAllByDateAndDoctor(dateString, this.$store.state.auth.user.id);
    this.connectAndSubscribe();
  }
}
</script>

<style scoped>

</style>