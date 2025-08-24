<script setup>
import PaymentRequestForm from './components/PaymentRequestForm.vue'
import FetchAllPayments from './components/FetchAllPayments.vue'
</script>

<template>
  <div class="center-container">
    <h1>PAYMENT INSTRUCTION SERVICE</h1>
    <PaymentRequestForm />
  </div>

  <div class="center-container">
    <h2>Retrieve existing payments</h2>
    <FetchAllPayments />
  </div>
</template>

<style scoped>
.center-container {
  /*
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  min-height: 20vh;
  */
  margin-left: 24px;
}
h1,
h2 {
  //text-align: center;
  margin-bottom: 20px;
  font-family: 'Roboto', sans-serif;
}
</style>
