import { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {

  const [student, setStudent] = useState({
    name: "",
    subject1: "",
    subject2: "",
    subject3: ""
  });

  const [result, setResult] = useState(null);

  const handleChange = (e) => {
    setStudent({
      ...student,
      [e.target.name]: e.target.value
    });
  };

  const calculateGrade = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/calculate",
        {
          name: student.name,
          subject1: parseInt(student.subject1),
          subject2: parseInt(student.subject2),
          subject3: parseInt(student.subject3)
        }
      );

      setResult(response.data);

    } catch (error) {
      console.error("Error:", error);
      alert("Something went wrong!");
    }
  };

  return (
    <div className="container">
      <h1>Student Grade Calculator</h1>

      <input type="text" name="name" placeholder="Name" onChange={handleChange} />
      <input type="number" name="subject1" placeholder="Enter Marks of Subject 1" onChange={handleChange} />
      <input type="number" name="subject2" placeholder="Enter Marks of Subject 2" onChange={handleChange} />
      <input type="number" name="subject3" placeholder="Ente Marks of Subject 3" onChange={handleChange} />

      <button onClick={calculateGrade}>Calculate</button>

      {result && (
        <div className="result">
          <p>Total: {result.total}</p>
          <p>Average: {result.average}</p>
          <p>Grade: {result.grade}</p>
        </div>
      )}
    </div>
  );
}

export default App;
