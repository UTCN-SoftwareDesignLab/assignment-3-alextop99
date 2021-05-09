<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>{{ createPatient ? "Create Patient" : "Edit Patient" }}</h1>
      <v-form>
        <v-text-field v-model="patient.firstname" label="Firstname" />
        <v-text-field v-model="patient.lastname" label="Lastname" />
        <v-text-field v-model="patient.idcardnumber" label="Id Card Number" />
        <v-text-field v-model="patient.cnp" label="CNP" />
        <v-text-field v-model="patient.birthdate" label="Birth Date" />
        <v-text-field v-model="patient.address" label="Address" />
      </v-form>
      <v-card-actions>
        <v-btn @click="Execute">
          {{ createPatient ? "Create" : "Save" }}
        </v-btn>
        <v-btn v-if="!createPatient" @click="Delete">Delete Patient</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientDialog",
  props: {
    patient: Object,
    onClose: Function,
    createPatient: Boolean,
    visible: Boolean,
  },
  methods: {
    Execute() {
      if (this.createPatient) {
        api.patients
            .create({
              id : null,
              firstname: this.patient.firstname,
              lastname: this.patient.lastname,
              idcardnumber: this.patient.idcardnumber,
              cnp: this.patient.cnp,
              birthdate: this.patient.birthdate,
              address: this.patient.address,
            })
            .then(() => this.$router.go());
      } else {
        api.patients
            .update({
              id: this.patient.id,
              firstname: this.patient.firstname,
              lastname: this.patient.lastname,
              idcardnumber: this.patient.idcardnumber,
              cnp: this.patient.cnp,
              birthdate: this.patient.birthdate,
              address: this.patient.address,
            })
            .then(() => this.$router.go());
      }
    },
    Delete(){
      api.patients.deleteById(this.user.id).then(() => this.$router.go());
    },
  },
};
</script>

<style scoped></style>
