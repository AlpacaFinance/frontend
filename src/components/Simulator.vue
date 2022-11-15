<template>
  <v-card
      class="pa-5 rounded-xl">
    <v-card-title>
      Simula tu Operación
    </v-card-title>
    <v-spacer></v-spacer>
    <v-form v-model="valid">
      <v-container>
        <v-row>
          <v-col cols="6">
              ¿Con qué divisa deseas realizar la simulación?
            <v-sheet>
              <v-switch
                  v-model="divisa"
                  inset
                  :label="`Divisa: ${messages}`"
              ></v-switch>
            </v-sheet>
          </v-col>

          <v-col>
              ¿Cúal es el importe que deseas simular?

            <v-text-field
                v-model="amount"
                :rules="importRules"
                label="S/ 0.00"
                required
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="6">
              ¿Cúal es el tipo de la tasa de interés?
            <v-select
                v-model="select"
                :items="items"
                :error-messages="errors"
                label="Select"
                data-vv-name="select"
                required
            ></v-select>
          </v-col>

          <v-col>
              ¿Cúal es la tasa de interés?
            <v-text-field
                v-model="rate"
                :rules="rateRules"
                label="0%"
                required
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>

  </v-card>
</template>

<script>
  export default {
    name: 'Simulator',
    data: () => ({
      divisa:false,
      valid:false,
      amount: '',
      amountRules: [
        v => !!v || 'Import is required',
      ],
      rate: '',
      rateRules: [
        v => !!v || 'Import is required',
      ],
      select: null,
      items: [
        'Item 1',
        'Item 2',
        'Item 3',
        'Item 4',
      ],
    }),
    computed: {
      messages () {
        return this.divisa ? ['Dolares'] : ['Soles']
      },
    },
  }
</script>
