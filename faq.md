# Fragen und Antworten

## Allgemein

- **Wie soll das Endprodukt aussehen?**
    - Sie sollen das fertige Softwareprojekt als Repository bereitstellen.
    - In der Datei `README.md` soll beschrieben sein, wie man die Software ausführt.
    - Die Software soll über die Kommandozeile gestartet werden können, wobei man die Eingabedatei (Spielergebnisse) als Kommandozeilenparameter angeben kann.
- **Wie soll man es ausführen?**
    - Über einen Kommandozeilenbefehl, der im `README.md` zu dokumentieren ist.
- **Wo soll es gespeichert werden und wie?**
    - Sie erstellen ein persönliches Repository auf unserem Git-Server [code.frickelbude.ch](https://code.frickelbude.ch).
- **Was ist der beabsichtigte Zweck des Programms?**
    - Die Berechnung von Ligatabellen anhand von Spielergebnissen. Die Ligatabellen können anschliessend veröffentlicht werden, beispielsweise auf einer Webseite oder in einer Zeitung.
- **Welche Frist ist für dieses Projekt vorgesehen, und gibt es bestimmte Meilensteine oder Ergebnisse, die auf dem Weg dorthin erreicht werden müssen?**
    - Das Projekt sollte in der Woche vom 26. Juni abgeschlossen werden, damit bis Ende Schuljahr noch eine Rückmeldung möglich ist, und die Arbeit für die Rundung der Semesternote berücksichtigt werden kann.
- **Gibt es bestimmte Anforderungen für die Benutzeroberfläche?**
    - Sie liefern nur ein Kommandozeilenprogramm ab. Wie dieses dann in ein bestehendes System integriert wird, braucht Sie nicht zu kümmern.
- **Wie werden die Benutzer das Programm verwenden?**
    - Das Programm muss über die Kommandozeile ansprechbar sein.
- **Was ist das Budget für das Projekt?**
    - Die Menge an Zeit, die Sie bereit sind darin zu investieren.
- **Soll ein weiteres Frontend für die Anwendung entwickelt werden?**
    - Nein, es soll nur eine Kommandozeilenanwendung entwickelt werden.
- **Müssen Parameter mitgegeben werden (z.B. Liga)?**
    - Als Kommandozeilenparameter wird eine Eingabedatei erwartet, sonst nichts.
- **Lohnt es sich nach einem bereits bestehenden Tool für diese Aufgabe zu suchen?**
    - Ein fertiges Tool ist dafür wohl nicht geeignet, da wir sehr spezifische Formatierungsregeln haben.
    - Die Programmlogik ist so einfach, dass sich auch kaum die Einbindung einer Library lohnen dürfte.
    - Es gibt jedoch domänenspezifische Programmiersprachen (Domain-Specific Language; DSL), mit der sich solche Aufgaben recht einfach bewältigen lassen. Ein Beispiel dafür ist [`awk`](https://de.wikipedia.org/wiki/Awk).
- **Welche Programmiersprache wird erwartet?**
    - Sie können sich selber für eine Programmiersprache entscheiden, sofern diese eine frei verfügbare Implementierung für Windows, Linux und macOS bietet.
    - Beispielprojekte können nach Bedarf in Python, Go und C# zur Verfügung gestellt werden.
    - Hilfestellungen kann ich Ihnen in den folgenden Programmiersprachen bieten: AWK, Bash, C, Clojure, C#, Elixir, Go, Java, JavaScript, Lua, PHP, Python, R, Racket, Ruby, Rust, Scheme.
- **Was ist das Ziel des Projekts?**
    - Sie sollen eine typische Anwendung der Datenverarbeitung komplett selbständig umsetzen können.
- **Werden für das Projekt bestimmte Programmier- oder Datenanalyse-Tools benötigt?**
    - Nein, das Problem lässt sich mit allen gängigen Hochsprachen lösen.
- **Werden bestimmte Fähigkeiten in Bezug auf Mathematik, Statistik oder Datenanalyse benötigt?**
    - Mathematik: Grundschularithmetik sollte reichen.
    - Datenanalyse: Sie müssen die Input-Datei auslesen können.
- **Werden bestimmte Fähigkeiten in Bezug auf Programmierung benötigt, und wenn ja, in welcher Programmiersprache?**
    - Sie müssen geeignete Datenstrukturen definieren bzw. auswählen können.
    - Sie müssen Konstrukte wie Schleifen und Verzweigungen beherrschen.
    - Sie müssen Daten formatiert einlesen und wieder ausgeben können.
- **Werden bereits vorhandene Daten oder Ressourcen verwendet oder müssen neue Daten erhoben werden?**
    - Sämtliche Beispieldateien werden Ihnen zur Verfügung gestellt.

## Format der Daten

- **Verändern sich die Daten? Kommen neue Daten hinzu? Ändert sich das Format der Daten?**
    - Das Format der Daten ändert sich nicht, es können aber weitere Daten hinzukommen (zusätzliche Dateien, zusätzliche Zeilen in bestehenden Dateien, andere Reihenfolge und Ergebnisse in bestehenden Dateien). Wenn Ihr Programm mit den bestehenden Daten funktioniert, sollte es auch für weitere Daten funktionieren.
- **Gibt es Unregelmässigkeiten? (Doppelpunkt in name)**
    - Nein, der Doppelpunkt taucht in keinem Mannschaftsnamen auf.
- **Kann davon ausgegangen werden, dass die Anzahl Tore immer ohne Abstand zum Doppelpunkt sind?**
    - Ja, das Format wurde so festgelegt.
- **Welches Format wird der Input Datei haben?**
    - Siehe Beispiele im `input`-Verzeichnis.
- **Welches Format soll der Output-Datei haben?**
    - Siehe Beispiel in `output/bundesliga.txt`.
- **Gibt es besondere Anforderungen an die Formatierung oder Ausgabe der Programmergebnisse?**
    - Die Ausgabe soll in eine Textdatei erfolgen (UTF-8-kodiert).
- **Gibt es besondere Anforderungen an die Art und Weise, wie die Eingabedaten bereitgestellt oder formatiert werden sollten?**
    - Die Eingabedatei wird über das Dateisystem zur Verfügung gestellt.
    - Die Formatierung ist immer analog zu den Beispieldaten im `input`-Verzeichnis.
- **Wird die Heimmannschaft immer zuerst angegeben? (Auch bei amerikanischen Ligen?)**
    - Ja, die Heimmannschaft wird immer zuerst angegeben. Die Unterscheidung in Heim- und Auswärtsmannschaft ist jedoch für das Ergebnis irrelevant, es handelt sich dabei nur um eine Konvention.
- **Sind Sonderzeichen in den Klubnamen, wie “:” ausgeschlossen?**
    - Die Klubnamen bestehen ausschliesslich aus alphanumerischen Zeichen (Zahlen und Buchstaben) und Interpunktionszeichen (Punkt, Bindestrich), wobei der Doppelpunkt explizit ausgeschlossen ist. Umlaute (ä, ö, ü) können vorkommen.
- **Kommt der Input immer korrekt formatiert daher?**
    - Ja, die Daten werden immer korrekt formatiert geliefert. Ist dies nicht der Fall, soll keine Tabelle erstellt werden.
- **Muss die Ausgabe am Ende genau so aussehen wie in der Beispieldatei?**
    - Ja, das Format soll möglichst identisch sein. Wenn Sie die Datei `input/bundesliga.txt` verarbeiten, soll die Ergebnisdatei `output/bundesliga.txt` identisch mit der gegebenen Datei sein.
- **Was bedeuten die Abkürzungen und die Zeichen?**
    - Die Spalten in der Ausgabetabelle haben die folgende Bedeutung:
        - `#`: Rang von 1 bis n
        - `Team`: Name der Mannschaft
        - `W`: Anzahl Siege ("Won")
        - `T`: Anzahl Unentschieden ("Tied")
        - `L`: Anzahl Niederlagen ("Lost")
        - `+`: Anzahl erzielte Tore
        - `-`: Anzahl kassierte Tore
        - `=`: Tordifferenz
        - `P`: Anzahl Punkte
- **Was für ein Format muss die Ausgabe haben? (word, txt, …)**
    - Verwenden Sie Textdateien mit der Codierung UTF-8, d.h. analog zu den Eingabedateien.
- **Wie wird der Output ausgegeben? Als Datei, nur Konsolenausgabe…?**
    - Die Ausgabe erfolgt immer in eine Textdatei.

## Programmlogik

- **Ist die Annahme richtig, dass bei einer Gleichen Punkteanzahl als erstes auf die Anzahl verlorene Spiele geschaut wird?**
    - Nein; die Sortierreihenfolge lautet:
        1. Punkte (absteigend)
        2. Tordifferenz (absteigend)
        3. Anzahl Siege (absteigend)
        4. Mannschaftsname (aufsteigend)
- **Wie funktioniert die Sortierreihenfolge wenn beide Teams die selben Statistiken haben?**
    - In diesem Fall kann aufsteigend nach dem Mannschaftsnamen sortiert werden.
- **Wie werden im Fussball die Spielergebnisse ausgewertet, wie werden die Punkte gezählt?**
    - Ein Sieg ergibt drei Punkt, ein Unentschieden einen Punkt (für beide Mannschaften), eine Niederlage null Punkte.
- **Was passiert, wenn zwei Mannschaften die gleiche Punktzahl haben?**
    - Es wird nach Tordifferenz und Anzahl Siegen weitersortiert.
    - Alle Spalten sind rechtsbündig auszurichten.
    - Es ist genügend Platz für die Mannschaftsnamen zu lassen (zweite Spalte); 30 Zeichen sollten genügen.
- **Gibt es beim Umgang mit den Eingabe- oder Ausgabedaten Sicherheits- oder Datenschutzprobleme zu beachten?**
    - Nein; die Daten sind öffentlich verfügbar und darum nicht schützenswert.
- **Zählt die Auswärtstorregel?**
    - Nein, es gibt keine Auswärtstorregel zu beachten.
- **Sind diese Fachlichen Anforderungen pro Liga unterschiedlich?**
    - Nein, es können die gleichen Regeln für alle Eingabedaten verwendet werden.
- **Soll nur der Sport Fussball ausgewertet werden; wenn nein: welche noch?**
    - Es sollen nur Fussballergebnisse in Tabellen umgewandelt werden können.
- **Könnte man Regular Expression verwenden?**
    - Reguläre Ausdrücke können beim Auslesen der Eingabedatei sehr sinnvoll eingesetzt werden, gerade beim Verarbeiten einer einzelnen Zeile.
- **Ich weiss nicht, wie man die anz. der kassierten Tore bekommen soll; ob man das mit z.B. einer Map lösen könnte.**
    - Grundsätzlich ist eine Map eine sinnvolle Datenstruktur für das vorliegende Problem: Der Mannschaftsname ist der Key, die restlichen Angaben können in eine weitere Datenstruktur zusammengefasst als Value verwaltet werden.
    - Wenn Sie einen Mechanismus für die erzielten Tore haben, können Sie den gleichen Mechanismus auch für die kassierten Tore verwenden. (Die Tordifferenz lässt sich dann aus diesen beiden Angaben berechnen.)
- **Wie wird der Output ausgegeben?**
    - Die meisten Programmiersprachen unterstützen formatierte Textausgaben. Bei Go verwenden sie beispielsweise `fmt.Printf`, bei Python f-Strings usw.
- **Muss die Ausgabe auf eine neue/leere Tabelle hinzugefügt werden oder auf eine bereits erstellte ergänzt werden?**
    - Es wird immer eine neue Datei erstellt.
- **Wie können die Werte jeder Zeile und jedes Ergebnisses getrennt werden?**
    - Sie können die Eingabedatei anhand des Zeilenumbruchs `\n` in einzelne Ergebniszeilen auftrennen.
    - Die einzelnen Ergebniszeilen können Sie mit Split-Operationen (u.a. beim Doppelpunkt und bei Leerzeichen) aufteilen oder mit regulären Ausdrücken auslesen.

## Nicht-funktionale Anforderungen

- **Sollen Tests für das Programm geschrieben werden?**
    - Ja, automatische Tests sind als Unit Tests (einzelne Funktionen/Methoden) oder Integrationsstests (Vergeleich der Ausgabe mit einer Vorgabe) sinnvoll.
- **Gibt es Anforderungen bezüglich dem Ausführungszeit?**
    - Eine Eingabedatei mit ca. 300 Zeilen sollte in ungefähr einer Sekunde verarbeitet werden können. (Mit moderner Hardware sollte das kein Problem sein.)
- **Wie ich `void`-Methoden mit Unittests am besten realisieren?**
    - Funktionen/Methoden ohne Rückgabewert haben in der Regel Seiteneffekte, die sich überprüfen lassen, z.B. eine Datei, die erstellt wird.
    - Unittests überprüfen in der Regel einen Rückgabewert; soll eine Seiteneffekt getestet werden, ist eher von einem Integrations- oder Systemtests die Rede.
    - Evtl. kann die `void`-Methode angepasst werden, sodass sie einen Wert zurückgibt und nicht einen Seiteneffekt hat.
- **Wie kann ich die Performance meines Programms verbessern? Ist Multithreading sinnvoll?**
    - Programme sind meistens dann performant, wenn ihnen ein gutes Design zugrunde liegt.
    - Performanceoptimierung sollte man erst dann betreiben, wenn das Programm nachweislich _zu_ langsam läuft.
    - Threads machen ein Programm i.d.R. komplizierter und fehleranfälliger. Wenn Nebenläufigkeit verwendet werden soll, gibt es sicherere Ansätze (z.B. ein `parallelStream` in Java, oder `pmap` in Clojure).
- **Welche Programmiersprache ist bezogen auf die Ausführungsgeschwindigkeit (Performance) die beste für die vorliegende Aufgabe?**
    - Die vorliegende Aufgabe lässt sich mit praktisch allen gängigen Programmiersprachen so lösen, dass die Ausführungsgeschwindigkeit kein Problem darstellen sollte.
    - Für viele Aufgaben lassen sich Programmiersprachen in drei Performanceklassen einteilen:
        - _schnell_: kompilierte Sprachen ohne Garbage Collection wie C, C++, D, Rust, nim
        - _mittel_: Sprachen mit Garbage Collection (kompiliert zu Maschinen- oder Bytecode) wie Go, Java, C#, Common Lisp
        - _langsam_: rein interpretierte Skriptsprachen wie Python, Ruby, Perl, PHP
        - siehe auch [Benchmarks Game](https://benchmarksgame-team.pages.debian.net/benchmarksgame/index.html) für detailliertere Vergleiche
- **Soll das Programm auf allen Betriebssystemen oder nur unter Windows laufen?**
    - Das Programm muss unter Windows, Linux und macOS lauffähig sein. Für die meisten gängigen Sprachen gibt es Implementierungen für alle diese Plattformen.
- **Sollen bestimmte Ligen automatisch beim z.B. PC Startup herausgerechnet werden?**
    - Nein, das ist nicht nötig. Sie brauchen sich nicht um die Integration Ihrer Software zu kümmern.
- **Ich kenne Netbeans und dort kann man Projekte mit einem GUI machen, werden wir das in der Richtung machen?**
    - Nein, es soll kein GUI erstellt werden. Sie können aber gerne Netbeans für die Entwicklung verwenden.
- **Wie lässt sich das Programm ohne unnötige Repetition im Code umsetzen?**
    - Am besten lösen Sie das Problem zuerst einmal, und schauen dann, ob Sie repetitiven Code haben. Diesen können Sie dann durch Auslagerungen in Funktionen/Methoden oder separaten Datenstrukturen von Repetitionen befreien.

## Annahmen

**Korrekte Annahmen**

- Teams haben keinen Doppelpunkt im Namen 
- Punktzahl ist durch einen Space vom Team getrennt 
- Score ist immer mit einem Doppelpunkt separiert 
- Pro Zeile 2 Teams und der Score 
- Score in der Mitte zwischen den beiden Teams 
- Ausgabe in txt file 
- Es werden nur .txt Dateien im Input-Ordner sein
- 3 Punkte für Sieg, 1 Punkt für Unentschieden, 0 Punkte für Niederlage
- Jedes Team hat einen anderen Namen (Eindeutig) 
- Pro Zeile gibt es ein neues Match
- Es gibt mehrere möglichen Sprachen, in denen man das lösen kann.

**Fragliche und falsche Annahmen**

- Muss erweiterbar sein 
    - Das gestellte Problem muss gelöst werden!
- Sortierreihenfolge: Punkte > Tordifferenz > Tore geschossen
    - Punkte > Tordifferenz > **Anzahl Siege**
- Das Projekt kann sehr lange gehen 
    - Ende Semester sollte es fertig sein
- Das Projekt könnte einige Zeit in Anspruch nehmen.
    - Ja, wir haben dafür bis Ende Semester Zeit
- Es könnte sehr spannend werden.
    - Durchaus :-)