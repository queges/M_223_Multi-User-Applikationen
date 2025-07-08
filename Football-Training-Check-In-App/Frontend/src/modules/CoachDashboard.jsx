import { useEffect, useState } from "react";
import "../styles/CoachDashboard.css";

export default function CoachDashboard() {
  const [trainings, setTrainings] = useState([]);
  const [startTime, setStartTime] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));
  const nextTraining = trainings
  .filter(t => new Date(t.startTime) > new Date()) // nur zukünftige
  .sort((a, b) => new Date(a.startTime) - new Date(b.startTime))[0]; // das nächste


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
    <div className="coach-dashboard">
      <h2>Welcome Coach {user.username}</h2>
      <div className="dashboard-grid">
        {/* Events */}
        <div>
          <h3 className="section-title">Events</h3>
          {trainings.map((t) => (
            <div key={t.id} className="training-box">
              <div className="event">
                {new Date(t.startTime).toLocaleString()}
              </div>
              <button className="delete-button">X</button>
            </div>
          ))}
        </div>

        {/* Attendance */}
<div>
  <h3 className="section-title">Next Events attendance</h3>
  {nextTraining ? (
    <div>
      <p className="event">
        {new Date(nextTraining.startTime).toLocaleString()}
      </p>

      {Array.isArray(nextTraining.attendances) &&
      nextTraining.attendances.length > 0 ? (
        nextTraining.attendances.map((a, i) => (
          <div key={i} className="attendance-entry">
            <div className="attendance-name">
              {a.user?.username || "Unbekannt"}
            </div>
            <div
              className={`attendance-status ${
                a.status === "YES"
                  ? "yes"
                  : a.status === "NO"
                  ? "no"
                  : "unknown"
              }`}
            ></div>
          </div>
        ))
      ) : (
        <p>Keine Spielerantworten vorhanden</p>
      )}
    </div>
  ) : (
    <p>Kein zukünftiges Training gefunden</p>
  )}
</div>

        

        {/* Add Events */}
        <div>
          <h3 className="section-title">Add Events</h3>
          <div className="add-event-form">
            <label>Date:</label>
            <input
              type="datetime-local"
              value={startTime}
              onChange={(e) => setStartTime(e.target.value)}
            />
            <button onClick={createTraining}>Add</button>
          </div>
        </div>
      </div>
    </div>
  );
}
