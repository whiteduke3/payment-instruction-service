<script setup>
import PaymentRequestForm from './components/PaymentRequestForm.vue'
</script>

<template>
  <div class="center-container">
    <h1>PAYMENT INSTRUCTION SERVICE</h1>
    <PaymentRequestForm />
  </div>
</template>

<style scoped>
.center-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  min-height: 20vh;
}
h1 {
  text-align: center;
  margin-bottom: 24px;
  font-family: 'Roboto', sans-serif;
}
</style>
