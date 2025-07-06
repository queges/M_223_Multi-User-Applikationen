import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/auth.service";

export default function Logout() {
  const navigate = useNavigate();

  useEffect(() => {
    AuthService.logout();       // User ausloggen (localStorage löschen)
    navigate("/login");         // Zur Login-Seite weiterleiten
  }, [navigate]);

  return <p>Logge aus...</p>;
}
