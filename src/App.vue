<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>

      <v-btn
          v-for="(item, index) in rutes" :key="index"
          text
          @click="insertRutes(item.rute)"
      >
        <span class="mr-2">{{item.name}}</span>
      </v-btn>


    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    rutes:[
      { name: 'Simulador', rute: '/simulador' },
      { name: 'Historial', rute: '/historial' }
    ]
  }),

  methods: {
    insertRutes(rute){
      this.$router.push(rute);
    }
  }
};
</script>
