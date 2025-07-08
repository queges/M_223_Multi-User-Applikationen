import { useEffect, useState } from "react";
import PlayerDashboard from "./PlayerDashboard";
import CoachDashboard from "./CoachDashboard";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
  const [role, setRole] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) {
      navigate("/login");
      return;
    }

    const isAdmin = user.roles.includes("ROLE_ADMIN");
    const isUser = user.roles.includes("ROLE_USER");


    if (isAdmin) setRole("admin");
    else if (isUser) setRole("user");
    else navigate("/login");
  }, [navigate]);

  if (!role) return <p>Lade Dashboard...</p>;
  if (role === "admin") return <CoachDashboard />;
  return <PlayerDashboard />;
}
