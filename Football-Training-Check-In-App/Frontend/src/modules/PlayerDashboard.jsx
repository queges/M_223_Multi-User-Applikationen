import { useEffect, useState } from "react";

export default function PlayerDashboard() {
  const [trainings, setTrainings] = useState([]);
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    fetch("http://localhost:8080/api/trainings", {
      headers: {
        Authorization: `Bearer ${user.token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => setTrainings(data));
  }, [user.token]);

  const respond = (trainingId, status) => {
    fetch(`http://localhost:8080/api/trainings/${trainingId}/attendance`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${user.token}`,
      },
      body: JSON.stringify({ status }),
    }).then(() => alert("Antwort gespeichert."));
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Kommende Trainings</h2>
      <ul>
        {trainings.map((t) => (
          <li key={t.id} className="mb-2 border p-2 rounded">
            <p>{new Date(t.startTime).toLocaleString()}</p>
            <button
              className="bg-green-500 text-white px-2 py-1 mr-2 rounded"
              onClick={() => respond(t.id, "YES")}
            >
              Ich komme
            </button>
            <button
              className="bg-red-500 text-white px-2 py-1 rounded"
              onClick={() => respond(t.id, "NO")}
            >
              Ich komme nicht
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}