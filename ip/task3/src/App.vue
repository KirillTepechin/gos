<template>
  <h1>Погода на ближайшую неделю</h1>
  <div id="cards">
    <WeatherCard v-for="item in weatherInfos" :key="item.date" :temp_max="item.temp_max" :temp_min="item.temp_min" :date="item.date"/>
  </div>
  
</template>
<script setup lang="ts">
  import WeatherCard from './components/WeatherCard.vue'
</script>
<script lang="ts">
export default{
  data() {
    return { 
      currentTemp: Number,
      weatherInfos: []
    }
  },
  mounted(){
    /*
    fetch("https://api.open-meteo.com/v1/forecast?latitude=54.31&longitude=48.41&daily=temperature_2m_max,temperature_2m_min&current=temperature_2m")
      .then(response=>response.json())
      .then(data=>{
        console.log(data)
        this.weatherInfos = data.daily.temperature_2m_max.map((tempMax, index) => ({
          time: data.daily.time[index],
          temp_max: tempMax,
          temp_min: data.daily.temperature_2m_min[index]
        }));
        this.currentTemp=data.current.temperature_2m
      })*/
      
      fetch("https://www.7timer.info/bin/api.pl?lon=113.17&lat=23.09&product=civillight&output=json")
      .then(response=>response.json())
      .then(data=>{
        let dataseries = data.dataseries
        this.weatherInfos = dataseries.map((dataseria, index) => ({
          date: dataseria.date as String,
          temp_max: dataseria.temp2m.max,
          temp_min: dataseria.temp2m.min
        }));
      })
  }
}

</script>
<style scoped>
#cards{
  display: flex;
}
</style>
