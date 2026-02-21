import { useState } from "react";
import axios from "axios";
import "./App.css";

const BASE_URL = "http://localhost:8080/api/currency";

function App() {
  const [amount, setAmount] = useState("");
  const [baseCurrency, setBaseCurrency] = useState("USD");
  const [targetCurrency, setTargetCurrency] = useState("INR");
  const [result, setResult] = useState("");
  const [history, setHistory] = useState([]);

  const convertCurrency = async () => {
    if (!amount || amount <= 0) {
      alert("Enter valid amount");
      return;
    }

    const response = await axios.post(`${BASE_URL}/convert`, {
      baseCurrency,
      targetCurrency,
      amount,
    });

    setResult(
      `${amount} ${baseCurrency} = ${response.data.convertedAmount} ${targetCurrency}`
    );
  };

  const loadHistory = async () => {
    const response = await axios.get(`${BASE_URL}/history`);
    setHistory(response.data);
  };

  return (
    <div className="container">
      <h1>Currency Converter</h1>

      <div className="converter">
        <input
          type="number"
          placeholder="Enter Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />

        <select
          value={baseCurrency}
          onChange={(e) => setBaseCurrency(e.target.value)}
        >
          <option value="USD">USD</option>
          <option value="INR">INR</option>
          <option value="EUR">EUR</option>
          <option value="GBP">GBP</option>
        </select>

        <span>to</span>

        <select
          value={targetCurrency}
          onChange={(e) => setTargetCurrency(e.target.value)}
        >
          <option value="INR">INR</option>
          <option value="USD">USD</option>
          <option value="EUR">EUR</option>
          <option value="GBP">GBP</option>
        </select>

        <button onClick={convertCurrency}>Convert</button>

        <h2>{result}</h2>
      </div>

      <div className="history">
        <h2>Conversion History</h2>
        <button onClick={loadHistory}>Load History</button>

        <table>
          <thead>
            <tr>
              <th>Base</th>
              <th>Target</th>
              <th>Amount</th>
              <th>Converted</th>
            </tr>
          </thead>
          <tbody>
            {history.map((item, index) => (
              <tr key={index}>
                <td>{item.baseCurrency}</td>
                <td>{item.targetCurrency}</td>
                <td>{item.amount}</td>
                <td>{item.convertedAmount}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;