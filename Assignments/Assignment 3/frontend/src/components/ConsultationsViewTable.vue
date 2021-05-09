<template>
  <v-card>
    <v-card-title>
      Consultations
    </v-card-title>
    <v-data-table
        :headers="consultationsTableHeader"
        :items="consultations"
        @click:row="SelectConsultation"
    >
    </v-data-table>
    <ConsultationsEditDialog :visible="showConsultationDialog" :onClose="onClose" :consultationHistory="consultationHistory" :consultation="selectedConsultation"></ConsultationsEditDialog>
  </v-card>
</template>

<script>
import api from "@/api";
import ConsultationsEditDialog from "@/components/ConsultationsEditDialog";

export default {
  name: "ConsultationsViewTable",
  components: {ConsultationsEditDialog},
  props: {
    consultations: Array,
  },
  data() {
    return {
      consultationsTableHeader: [
        {
          text: "Date",
          value: "date",
          sortable: false,
        },
        {
          text: "Time",
          value: "time",
          sortable: false,
        },
        {
          text: "Patient",
          value: "patient.lastname",
          sortable: false,
        },
      ],
      consultationHistory: [],
      selectedConsultation: {'patient':{}, 'user':{}},
      showConsultationDialog: false,
    }
  },
  methods: {
    async SelectConsultation(consultation) {
      this.selectedConsultation = consultation;
      let date = new Date();
      let datestring = date.getFullYear() + "-" + ((date.getMonth() + 1) < 10 ? "0"+ (date.getMonth() + 1) : (date.getMonth() + 1)) + "-" + (date.getDate() < 10 ? "0"+ date.getDate() : date.getDate());

      this.consultationHistory = await api.consultations.getAllByPatientAndDoctor(consultation.patient.id, consultation.user.id, datestring);
      this.showConsultationDialog = true;
    },
    onClose() {
      this.showConsultationDialog = false;
    }
  }
}
</script>

<style scoped>
</style>