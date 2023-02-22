# Soccer Table Statistics

![Soccer Table Statistics Theme Image](https://code.frickelbude.ch/johannes_zeller/soccer-table/raw/branch/master/img/channels4_profile.jpg)

## How to run...

### ... on Windows (PowerShell)

If not done already, install JDK17:

- Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
- choco install microsoft-openjdk17 17.0.6 -y



- cmd /c "set INPUT_FILES_LOCATION=C:/path/to/folder/containing/your/input/files&& set OUTPUT_FILES_LOCATION=C:/path/to/folder/later/containing/your/output/files&& java -jar SoccerTableStats.jar"

### ... on Linux

If not done already, install JDK17:

- sudo apt install openjdk-17-jdk openjdk-17-jre

- env INPUT_FILES_LOCATION=~/path/to/folder/containing/your/input/files OUTPUT_FILES_LOCATION=~/path/to/folder/later/containing/your/output/files java -jar SoccerTableStats.jar

## Fragen und Annahmen

- Gibt es auch Teams mit einem Doppelpunkt im Namen? -> Annahme nein
- Kommt der Input immer richtig daher? -> Annahme für Anfang ja, Validierung von Input könnte später zusätzlich implementiert werden, falls der Kunde das wünscht
- Sind die Regeln gleich wie beim klassischen Fussball? -> Annahme ja
- Muss die Software auf Windows und / oder anderen Orten laufen können? -> Annamhe Windows, durch Entwicklung mit Java könnte das Ganze auch auf anderen Betriebssystemen laufen
- Braucht die Software ein GUI -> Annahme nein, könnte auch noch in Zukunft zusätzlich implementiert werden, falls der Kunde das wünscht