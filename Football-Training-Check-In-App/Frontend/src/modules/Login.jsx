import { useNavigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import React, { useState } from "react";

export default function Login() {
  const navigate = useNavigate();
  const [entries, setEntries] = useState({ username: "", password: "" });

  function store(e) {
    setEntries({ ...entries, [e.target.name]: e.target.value });
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    AuthService.login(entries.username, entries.password)
      .then((res) => {
        if (res && res.username) {
          localStorage.setItem("user", JSON.stringify(res));
          navigate("/dashboard");
        } else {
          alert("Login fehlgeschlagen");
        }
      })
      .catch((err) => {
        console.error(err);
        if (err.status === 401) {
          alert("Falscher Benutzername oder Passwort");
        } else {
          alert("Login fehlgeschlagen");
        }
      });
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit}>
        <h2>Login</h2>
        <div className="form-group">
          <label htmlFor="username">Benutzername:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={entries.username}
            onChange={store}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Passwort:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={entries.password}
            onChange={store}
            required
          />
        </div>
        <button type="submit">Login</button>
        <button type="button" onClick={() => navigate("/dashboard")}>Zum Dashboard wechseln</button>
      </form>
    </div>
  );
}
