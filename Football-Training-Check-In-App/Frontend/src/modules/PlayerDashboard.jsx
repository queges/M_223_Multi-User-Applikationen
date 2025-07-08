import { useEffect, useState } from "react";
import "../styles/PlayerDashboard.css";

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
    <div className="player-dashboard">
      <h2>Welcome {user.username}</h2>
      <div className="dashboard-grid">
        {/* Events */}
        <div>
          <h3 className="section-title">Events</h3>
          {trainings.map((t) => (
            <div key={t.id} className="event-box">
              {new Date(t.startTime).toLocaleString()}
            </div>
          ))}
        </div>

        {/* Attendance */}
        <div>
          <h3 className="section-title">Next Events attendance</h3>
          {trainings.map((t) => (
            <div key={t.id} className="attendance-group">
              <div className="attendance-time">
                {new Date(t.startTime).toLocaleString()}
              </div>
              <div className="attendance-buttons">
                <button
                  className="no"
                  onClick={() => respond(t.id, "NO")}
                >
                  ❌
                </button>
                <button
                  className="yes"
                  onClick={() => respond(t.id, "YES")}
                >
                  ✅
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
