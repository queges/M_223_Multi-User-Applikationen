# M_223_Multi-User-Applikationen
# 🏈 Football-Training Check-In App

Eine Multi-User-Webanwendung für American-Football-Teams.  
Spieler melden sich zu Trainings an oder ab, Trainer behalten den Überblick.

---

## ✅ User Stories mit Akzeptanzkriterien

### 👤 Spieler

**US01**: *Als Spieler will ich mich anmelden können, damit ich die App nutzen kann.*  
**Akzeptanzkriterien**:
- Ich kann mich mit E-Mail und Passwort einloggen.
- Bei falschen Daten erhalte ich eine Fehlermeldung.

**US02**: *Als Spieler will ich sehen, wann das nächste Training ist, damit ich Bescheid weiß.*  
**Akzeptanzkriterien**:
- Die Trainingstermine sind als Liste mit Datum und Uhrzeit sichtbar.
- Vergangene Trainings werden nicht angezeigt.

**US03**: *Als Spieler will ich sagen können, ob ich komme oder nicht, damit der Trainer planen kann.*  
**Akzeptanzkriterien**:
- Ich kann „Ich komme“ oder „Ich komme nicht“ auswählen.
- Meine Auswahl wird gespeichert und dem Trainer angezeigt.

### 🧑‍🏫 Trainer

**US04**: *Als Trainer will ich Trainings erstellen können, damit sich Spieler an-/abmelden können.*  
**Akzeptanzkriterien**:
- Ein Formular ermöglicht das Anlegen neuer Trainings mit Datum und Zeit.
- Neue Trainings erscheinen in der Liste.

**US05**: *Als Trainer will ich sehen, wer kommt und wer nicht, damit ich das Training planen kann.*  
**Akzeptanzkriterien**:
- Für jedes Training sehe ich eine Liste der angemeldeten und abgemeldeten Spieler.
- Leere Felder zeigen „Keine Antwort“.

---

## 📆 Arbeitsplan mit Aufwandsschätzung

| # | Aufgabe                              | Aufwand (h) | Status       |
|---|--------------------------------------|-------------|--------------|
| 1 | Projektsetup (Git, Supabase, Struktur) | 2           | ⏳ geplant   |
| 2 | Authentifizierung (JWT)               | 4           | ⏳ geplant   |
| 3 | Datenmodell erstellen (Supabase)      | 2           | ⏳ geplant   |
| 4 | User Stories umsetzen (Frontend + Backend) | 6       | ⏳ geplant   |
| 5 | An-/Abmeldung implementieren          | 4           | ⏳ geplant   |
| 6 | Trainer-Sicht entwickeln              | 4           | ⏳ geplant   |
| 7 | Tests (Frontend + Backend) schreiben  | 3           | ⏳ geplant   |
| 8 | Dokumentation + Deployment            | 3           | ⏳ geplant   |

---
