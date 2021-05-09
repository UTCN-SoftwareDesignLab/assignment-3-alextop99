<template>
  <v-card>
    <v-card-title>
      History
    </v-card-title>
    <v-data-table
        :headers="consultationsTableHeader"
        :items="consultationHistory"
    >
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  name: "ConsultationsHistory",
  props: {
    consultationHistory: Array,
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
          text: "Doctor",
          value: "user.username",
          sortable: false,
        },
        {
          text: "Details",
          value: "details",
          sortable: false,
        },
      ],
    }
  },
}
</script>

<style scoped>

</style>