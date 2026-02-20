import { useState } from "react";
import axios from "axios";
import "./App.css";   // ✅ IMPORTANT

function App() {

  const [accountNumber, setAccountNumber] = useState("");
  const [holderName, setHolderName] = useState("");
  const [amount, setAmount] = useState("");
  const [message, setMessage] = useState("");
  const [balance, setBalance] = useState(null);
  const [transactions, setTransactions] = useState([]);

  const BASE_URL = "http://localhost:8080/api/atm";

  const createAccount = async () => {
    try {
      await axios.post(`${BASE_URL}/create`, {
        accountHolderName: holderName,
        accountNumber: accountNumber,
        balance: 0
      });
      setMessage("Account Created Successfully");
    } catch (error) {
      setMessage("Error creating account");
    }
  };

  const deposit = async () => {
    try {
      const response = await axios.post(
        `${BASE_URL}/deposit?accountNumber=${accountNumber}&amount=${Number(amount)}`
      );

      setMessage(response.data.message);
      setBalance(response.data.balance);
      setAmount("");
    } catch (error) {
      setMessage("Deposit Failed");
    }
  };

  const withdraw = async () => {
    try {
      const response = await axios.post(
        `${BASE_URL}/withdraw?accountNumber=${accountNumber}&amount=${Number(amount)}`
      );

      setMessage(response.data.message);
      setBalance(response.data.balance);
      setAmount("");
    } catch (error) {
      if (error.response) {
        setMessage(error.response.data.message);
      } else {
        setMessage("Withdraw Failed");
      }
    }
  };

  const checkBalance = async () => {
    try {
      const response = await axios.get(
        `${BASE_URL}/balance/${accountNumber}`
      );

      setMessage(response.data.message);
      setBalance(response.data.balance);
    } catch (error) {
      setMessage("Error fetching balance");
    }
  };

  const getTransactions = async () => {
    try {
      const response = await axios.get(
        `${BASE_URL}/transactions/${accountNumber}`
      );
      setTransactions(response.data);
    } catch (error) {
      setMessage("Error fetching transactions");
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h2>ATM Management System</h2>

        <input
          type="text"
          placeholder="Account Holder Name"
          value={holderName}
          onChange={(e) => setHolderName(e.target.value)}
        />

        <input
          type="text"
          placeholder="Account Number"
          value={accountNumber}
          onChange={(e) => setAccountNumber(e.target.value)}
        />

        <input
          type="number"
          placeholder="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />

        <button className="create" onClick={createAccount}>Create</button>
        <button className="deposit" onClick={deposit}>Deposit</button>
        <button className="withdraw" onClick={withdraw}>Withdraw</button>
        <button className="balance-btn" onClick={checkBalance}>Balance</button>
        <button className="history" onClick={getTransactions}>History</button>

        <div className="message">{message}</div>
        {balance !== null && <div className="balance">Balance: ₹{balance}</div>}

        <div className="transactions">
          <ul>
            {transactions.map((txn) => (
              <li key={txn.id}>
                {txn.type} - ₹{txn.amount}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default App;
