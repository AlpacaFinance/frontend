<template>
  <v-card
      v-if="!editInfo"
      class="pa-5 rounded-xl">
    <div>
      <template>
        <v-row>
          <v-col
              class="d-flex child-flex"
          >
            <v-img
                :src="`https://upload.wikimedia.org/wikipedia/commons/0/0d/Camila_Cabello_Vogue_2.jpg`"
                :lazy-src="`https://upload.wikimedia.org/wikipedia/commons/0/0d/Camila_Cabello_Vogue_2.jpg`"
                aspect-ratio="1"
                width="25"
                class="grey lighten-2 rounded-circle"
            >
              <template v-slot:placeholder>
                <v-row
                    class="fill-height ma-0"
                    align="center"
                    justify="center"
                >
                  <v-progress-circular
                      indeterminate
                      color="grey lighten-5"
                  ></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-col>
        </v-row>
      </template>
    </div>
    <v-card-title>
      Diana Gómez
    </v-card-title>
    <v-card-subtitle>
      didi2000@test.com
    </v-card-subtitle>
    <v-card-text >
      <p class="font-weight-bold">DNI:</p>
      <p>__dni__</p>
      <p class="font-weight-bold">Pais:</p>
      <p>__country__</p>
      <p class="font-weight-bold">Número de contacto:</p>
      <p>__phone__</p>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: 'Profile',

  data: () => ({
    errors: [],
    editInfo: false,
    customer: [],
  }),
}
</script>
