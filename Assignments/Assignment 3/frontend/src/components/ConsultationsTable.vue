<template>
  <v-card>
    <v-card-title>
      Patients
    </v-card-title>
    <v-data-table
        :headers="consultationsTableHeader"
        :items="consultations"
        :search="consultationSearch"
        @click:row="SelectConsultation"
    >
      <template v-slot:top>
        <v-text-field
            v-model="consultationSearch"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>
    </v-data-table>
    <v-btn @click="CreateConsultation">
      Create Consultation
    </v-btn>
    <ConsultationsCreator :doctors="doctors" :patients="patients" :consultation="selectedConsultation" :visible="showConsultationDialog" :onClose="onClose" :createConsultation="isConsultationCreated" :CheckIn="CheckIn"/>
  </v-card>
</template>
<script>

import ConsultationsCreator from "@/components/ConsultationsCreator";
export default {
  name: "ConsultationsTable",
  components: {ConsultationsCreator},
  props: {
    consultations: Array,
    doctors: Array,
    patients: Array,
    CheckIn: Function,
  },
  data() {
    return {
      consultationSearch: "",
      consults: [],
      consultationsTableHeader: [
        {
          text: "Date",
          value: "date",
        },
        {
          text: "Time",
          value: "time"
        },
        {
          text: "Patient",
          value: "patient.lastname"
        },
        {
          text: "Doctor",
          value: "user.username"
        },
      ],
      selectedConsultation: {'patient':{}, 'user':{}},
      showConsultationDialog: false,
      isConsultationCreated: false,
    }
  },
  methods: {
    SelectConsultation(consultation) {
      this.selectedConsultation = consultation;
      this.isConsultationCreated = false;
      this.showConsultationDialog = true;
    },
    CreateConsultation() {
      this.selectedPatient = {};
      this.isConsultationCreated = true;
      this.showConsultationDialog = true;
    },
    onClose() {
      this.showConsultationDialog = false;
    }
  },
  created() {
    let date = new Date();
    this.consultationSearch = date.getFullYear() + "-" + (date.getMonth() + 1 < 10 ? "0"+ (date.getMonth() + 1) : (date.getMonth() + 1)) + "-" + (date.getDate() < 10 ? "0"+ date.getDate() : date.getDate());
  }
}
</script>

<style scoped></style>