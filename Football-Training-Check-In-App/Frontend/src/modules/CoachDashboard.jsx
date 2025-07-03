import { useEffect, useState } from "react";

export default function CoachDashboard() {
  const [trainings, setTrainings] = useState([]);
  const [startTime, setStartTime] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));

  const fetchTrainings = () => {
    fetch("http://localhost:8080/api/trainings", {
      headers: {
        Authorization: `Bearer ${user.token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => setTrainings(data));
  };

  useEffect(() => {
    fetchTrainings();
  }, []);

  const createTraining = () => {
    fetch("http://localhost:8080/api/trainings", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${user.token}`,
      },
      body: JSON.stringify({ startTime }),
    }).then(() => {
      alert("Training erstellt");
      setStartTime("");
      fetchTrainings();
    });
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Trainerübersicht</h2>

      <div className="mb-4">
        <input
          type="datetime-local"
          value={startTime}
          onChange={(e) => setStartTime(e.target.value)}
          className="border p-1 mr-2"
        />
        <button
          onClick={createTraining}
          className="bg-blue-500 text-white px-3 py-1 rounded"
        >
          Training erstellen
        </button>
      </div>

      <ul>
        {trainings.map((t) => (
          <li key={t.id} className="mb-2 border p-2 rounded">
            <p className="font-semibold">
              {new Date(t.startTime).toLocaleString()}
            </p>
            <ul className="ml-4 mt-2">
              {t.attendances?.map((a, i) => (
                <li key={i}>
                  {a.user?.username || "Unbekannt"}: {a.status || "Keine Antwort"}
                </li>
              )) || <li>Keine Rückmeldungen</li>}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
}
