<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>{{ createConsultation ? "Create Consultation" : "Remove Consultation" }}</h1>
      <v-card v-if="createConsultation" height="60vh">
        <v-label>Pick a Date</v-label>
        <datepicker :value="date" @selected="SelectedDate"></datepicker>
        <v-label v-if="dateSelected">Pick a Patient</v-label>
        <v-select v-if="dateSelected" :items="patientsList" @input="SelectPatient"></v-select>
        <v-label v-if="patientSelected">Pick a Doctor</v-label>
        <v-select v-if="patientSelected" :items="doctorsList" @input="SelectDoctor"></v-select>
        <v-label v-if="doctorSelected">Pick the Hour</v-label>
        <v-select v-if="doctorSelected" :items="hours" @input="SelectHour"></v-select>
        <v-btn v-if="hourSelected" @click="CreateAppointment">Create Consultation</v-btn>
      </v-card>
      <v-card v-else height="35vh">
        <v-label>Pick new Date</v-label><br/>
        <v-label>{{consultation.date}}</v-label><br/>
        <v-label>Patient</v-label><br/>
        <v-label>{{consultation.patient.firstname + " " + consultation.patient.lastname}}</v-label><br/>
        <v-label>Doctor</v-label><br/>
        <v-label>{{consultation.user.username}}</v-label><br/>
        <v-label>Hour</v-label><br/>
        <v-label>{{consultation.time}}</v-label><br/>
        <v-btn @click="DeleteConsultation">Delete Consultation</v-btn>
        <v-btn @click="CheckInBtn">Check-In</v-btn>
      </v-card>
    </v-card>
  </v-dialog>
</template>
<script>

import Datepicker from "vuejs-datepicker";
import api from "@/api";

export default {
  name: "ConsultationsCreator",
  components: {
    Datepicker
  },
  props: {
    consultation: Object,
    patients: Array,
    doctors: Array,
    onClose: Function,
    createConsultation: Boolean,
    visible: Boolean,
    CheckIn: Function,
  },
  data() {
    return {
      date: new Date(),
      patientsList: [],
      doctorsList: [],
      hours: ["08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"],
      availableHours: this.hours,
      patient: {},
      doctor: {},
      hour: "",
      dateSelected: false,
      patientSelected: false,
      doctorSelected: false,
      hourSelected: false,
    };
  },
  methods: {
    SelectedDate(date) {
      this.date = date;
      this.dateSelected = true;
      this.patientSelected = false;
      this.doctorSelected = false;
      this.hourSelected = false;
     },
    SelectPatient(patient) {
      this.patientSelected = true;
      this.doctorSelected = false;
      this.hourSelected = false;
      this.patient = patient;
    },
    async SelectDoctor(doctor) {
      this.doctorSelected = true;
      this.hourSelected = false;
      this.doctor = doctor;
      let datestring = this.date.getFullYear() + "-" + ((this.date.getMonth() + 1) < 10 ? "0"+ (this.date.getMonth() + 1) : (this.date.getMonth() + 1)) + "-" + (this.date.getDate() < 10 ? "0"+ this.date.getDate() : this.date.getDate());
      let queryResult = await api.consultations.getAllByDateAndDoctor(datestring, this.doctor.id);
      let occupiedHours = [];
      queryResult.forEach(element => occupiedHours.push(element.time.substring(0, 5)));
      this.availableHours = this.hours;

      for(let i = 0; i < this.availableHours.length; i++){
        occupiedHours.forEach(element => {
          if (element === this.availableHours[i]) {
            this.availableHours.splice(i, 1);
          }
        });
      }

    },
    SelectHour(hour) {
      this.hourSelected = true;
      this.hour = hour;
    },
    GetPatientSelected() {
      return this.patientsList.find(patient => patient.cnp === this.consultation.patient.cnp);
    },
    CreateAppointment() {
      let datestring = this.date.getFullYear() + "-" + ((this.date.getMonth() + 1) < 10 ? "0"+ (this.date.getMonth() + 1) : (this.date.getMonth() + 1)) + "-" + (this.date.getDate() < 10 ? "0"+ this.date.getDate() : this.date.getDate());
      let timestring = this.hour + ":00"

      let localPatient = {
        id: this.patient.id,
        firstname: this.patient.firstname,
        lastname: this.patient.lastname,
        idcardnumber: this.patient.idcardnumber,
        cnp: this.patient.cnp,
        birthdate: this.patient.birthdate,
        address: this.patient.address,
      };

      let localDoctor = {
        id: this.doctor.id,
        username: this.doctor.username,
        email: this.doctor.email,
        password: this.doctor.password,
        role: this.doctor.role,
      }

      api.consultations
          .create({
            id : null,
            date: datestring,
            time: timestring,
            patient: localPatient,
            user: localDoctor,
            details: "",
          })
          .then(() => this.$router.go());
    },
    DeleteConsultation() {
      api.consultations.deleteById(this.consultation.id).then(() => this.$router.go());
    },
    CheckInBtn() {
      this.CheckIn(this.consultation.patient.firstname + " " + this.consultation.patient.lastname, this.consultation.time, this.consultation.user.username);
    }
  },
  created() {
    this.patientsList = this.patients.map(patient =>  ({'value': patient,
                                                        'text': patient.firstname + " " + patient.lastname + " " + patient.cnp,
                                                      }));
    this.doctorsList = this.doctors.map(doctor => ({'value': doctor,
                                                    'text': doctor.username,
                                                      }));
  }
}
</script>

<style scoped>

</style>