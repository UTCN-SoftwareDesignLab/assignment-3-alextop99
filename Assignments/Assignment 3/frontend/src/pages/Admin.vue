<template>
  <v-container>
    <Header/>
    <v-layout row justify-center pt-3>
      <v-flex xs40 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Admin panel</h4>
            </v-card-title>
          </v-card>
          <v-card>
            <UserTable :users="users"/>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "@/api";
import UserTable from "@/components/UserTable";
import Header from "@/components/Header";
import router from "@/router";

export default {
  name: "Admin",
  components: { Header, UserTable},
  data() {
    return {
      users: [],
      userMode: true,
    };
  },
  async created() {
    if(this.$store.state.auth.user.role !== "ADMIN") {
      await this.$store.dispatch("auth/logout");
      await router.push("/");
    }
    else {
      this.users = await api.users.getAll();
    }
  },
};
</script>

<style scoped></style>