<template>
  <v-card>
    <v-card-title>
      Patients
    </v-card-title>
    <v-data-table
        :headers="patientTableHeader"
        :items="patients"
        :search="patientSearch"
        @click:row="selectPatient"
    >
      <template v-slot:top>
        <v-text-field
            v-model="patientSearch"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>
    </v-data-table>
    <v-btn @click="createPatient">
      Create Patient
    </v-btn>
    <PatientDialog :patient="selectedPatient" :visible="showPatientDialog" :onClose="onClose" :createPatient="isPatientCreated"/>
  </v-card>
</template>
<script>

import PatientDialog from "@/components/PatientDialog";
export default {
  name: "PatientTable",
  components: {PatientDialog},
  props: {
    patients: Array,
  },
  data() {
    return {
      patientSearch: "",
      patientTableHeader: [
        {
          text: "Firstname",
          value: "firstname",
        },
        {
          text: "Lastname",
          value: "lastname"
        },
        {
          text: "Id Card Nb",
          value: "idcardnumber"
        },
        {
          text: "CNP",
          value: "cnp"
        },
        {
          text: "Brith Date",
          value: "birthdate"
        },
        {
          text: "Address",
          value: "address"
        },
      ],
      selectedPatient: {},
      showPatientDialog: false,
      isPatientCreated: false,
    }
  },
  methods: {
    selectPatient(patient) {
      this.selectedPatient = patient;
      this.isPatientCreated = false;
      this.showPatientDialog = true;
    },
    createPatient() {
      this.selectedPatient = {};
      this.isPatientCreated = true;
      this.showPatientDialog = true;
    },
    onClose() {
      this.showPatientDialog = false;
    }
  }
}
</script>

<style scoped></style>