<template>
  <div>
    <button @click="fetchPayments">Fetch All Payments</button>
    <ul v-if="payments.length">
      <li v-for="payment in payments" :key="payment.id">
        <strong>ID:</strong> {{ payment.id }}<br />
        <strong>Amount:</strong> {{ payment.amount }}<br />
        <strong>Currency:</strong> {{ payment.currency }}<br />
        <strong>Debtor:</strong> {{ payment.debtorAccount }}<br />
        <strong>Creditor:</strong> {{ payment.creditorAccount }}<br />
        <strong>Reference:</strong> {{ payment.reference }}<br />
        <p></p>
      </li>
    </ul>
    <div v-else>No payments found.</div>
  </div>
</template>

<script setup lang="js">
import { ref } from 'vue'

const payments = ref([])

async function fetchPayments() {
  try {
    const baseUrl = 'http://localhost:8080'
    const response = await fetch(`${baseUrl}/api/fetchAllPayments`)
    if (!response.ok) throw new Error('Failed to fetch payments')
    payments.value = await response.json()
  } catch (error) {
    payments.value = []
    console.error(error)
  }
}
</script>

<style scoped>
button {
  padding: 8px 16px;
  margin-bottom: 16px;
  background: #1976d2;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background: #1565c0;
}

ul {
  margin: 0;
  padding: 0;
}

li {
  list-style: none;
  font-family: Roboto, sans-serif;
}
</style>
