<template>
    <div id="card">
        <div v-if="date!='Сейчас'">
            <p>{{ this.getNormalDate(date).toLocaleDateString(undefined, {weekday: 'long', month: 'long', day: 'numeric',}) }} </p>
            <p>От {{temp_min}} °C</p>
            <p>До {{ temp_max }} °C</p>
        </div>
        <div v-else>
            <p>{{ date }}</p>
            <p>{{ temp_max }} °C</p>
        </div>
    </div>
</template>

<script>
export default {
  props: {
    temp_max: Number,
    temp_min: Number,
    date: String
  },
  methods:{
     getNormalDate(dateString){
        
        const year = dateString.toString().substring(0, 4);
        const month = dateString.toString().substring(4, 6) - 1; // вычитаем 1, так как месяцы в JavaScript начинаются с 0
        const day = dateString.toString().substring(6, 8);

        const date = new Date(year, month, day);

        return date;
    }
  }
}
</script>

<style scoped>
#card{
    border-radius: 10px;
    margin: 10px;
    width: 200px;
    background-color: whitesmoke;
}
</style>
