<template>
  <v-container>
    <Header/>
    <v-layout row justify-center pt-3>
      <v-flex xs40 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Secretary panel</h4>
            </v-card-title>
            <v-btn @click="SwitchView">
              {{(patientMode) ? "Consultations" : "Patients"}}
            </v-btn>
          </v-card>
          <v-card v-if="patientMode">
            <PatientTable :patients="patients"/>
          </v-card>
          <v-card v-else>
              <ConsultationsTable :doctors="doctors" :patients="patients" :consultations="consultations" :CheckIn="CheckInBtn"></ConsultationsTable>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import PatientTable from "@/components/PatientTable";
import api from "@/api";
import Header from "@/components/Header";
import ConsultationsTable from "@/components/ConsultationsTable";

export default {
  name: "Secretary",
  components: {ConsultationsTable, Header, PatientTable},
  data() {
    return {
      patients: [],
      doctors: [],
      consultations: [],
      patientMode: true,
      connected: false,
    };
  },
  methods: {
    SwitchView() {
      this.patientMode = (!this.patientMode);
    },
    CheckInBtn(patientName, hour, doctorName) {

      api.webSocket.CheckIn(patientName, hour, doctorName);
    },
  },
  async created() {
    this.patients = await api.patients.getAll();
    this.doctors = await api.users.getAllDoctors();
    this.consultations = await api.consultations.getAll();
    this.connected = api.webSocket.connect();
  }
}
</script>
<style scoped></style>
