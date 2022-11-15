<template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <Profile />
      </v-col>

      <v-col cols="9">
        <Simulator />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import Simulator from '../components/Simulator'
  import Profile from "@/components/Profile";

  export default {
    name: 'Home',

    components: {
      Simulator,
      Profile
    },
  }
</script>
