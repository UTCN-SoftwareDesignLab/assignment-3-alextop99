<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>Consult</h1>
      <v-card>
        <v-label>Patient</v-label><br/>
        <v-label>{{consultation.patient.firstname + " " + consultation.patient.lastname}}</v-label><br/>
        <v-label>Hour</v-label><br/>
        <v-label>{{consultation.time}}</v-label><br/>
        <ConsultationsHistory :consultationHistory="consultationHistory"></ConsultationsHistory>
        <v-text-field v-model="consultation.details" label="Details" />
        <v-btn @click="Update">Save</v-btn>
      </v-card>
    </v-card>
  </v-dialog>
</template>

<script>
import ConsultationsHistory from "@/components/ConsultationsHistory";
import api from "@/api";
export default {
  name: "ConsultationsEditDialog",
  components: {ConsultationsHistory},
  props: {
    consultation: Object,
    consultationHistory: Array,
    visible: Boolean,
    onClose: Function,
  },
  methods: {
    Update() {
      api.consultations.update(this.consultation).then(() => this.$router.go());
    }
  }
}
</script>

<style scoped>

</style>