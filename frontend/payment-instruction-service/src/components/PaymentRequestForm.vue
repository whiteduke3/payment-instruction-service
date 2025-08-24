<template>
  <form @submit.prevent="submitForm">
    <input v-model="debtorAccount" placeholder="Debtor Account" required /><br />
    <input v-model="creditorAccount" placeholder="Creditor Account" required /><br />
    <input v-model.number="amount" type="number" placeholder="Amount" required /><br />
    <input v-model="currency" placeholder="Currency" required /><br />
    <input v-model="reference" placeholder="Reference" required /><br />
    <button type="submit">Submit</button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      debtorAccount: '',
      creditorAccount: '',
      amount: null,
      currency: '',
      reference: '',
    }
  },
  methods: {
    async submitForm() {
      const baseUrl = "http://localhost:8080"
      const payload = {
        debtor_account: this.debtorAccount,
        creditor_account: this.creditorAccount,
        amount: this.amount,
        currency: this.currency,
        reference: this.reference,
      }
      await fetch(`${baseUrl}/api/payment`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      })
    },
  },
}
</script>

<style scoped>
form input {
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 250px;
  font-size: 16px;
}
button {
  padding: 8px 16px;
  background: #1976d2;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background: #1565c0;
}
</style>
