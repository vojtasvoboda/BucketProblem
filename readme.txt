Problém přelévání vody
----------------------
Navrhněte a implementujte heuristiku řešící zobecněný problém přelévání vody. Heuristiku otestujte na všech zkušebních instancích a srovnejte s prohledáváním stavového prostoru do šířky (BFS). Volitelně srovnejte i s prohledáváním do hloubky (DFS).

Zvolenou heuristiku popište ve zprávě, která bude obsahovat:
- stručný popis použité heuristiky
- délku cesty k jednotlivým řešením (tedy počet manipulací s kbelíky)
- počet navštívených bodů stavového prostoru pro jednotlivé instance (je to lepší kritérium kvality heuristiky než výpočetní čas), pro Vaší heuristiku a prohledávání do šířky. Dobrovolně i pro prohledávání do hloubky (DFS)
- odkaz na zdrojové texty v souboru

Problém přelévání vody a zkušební instance
------------------------------------------
Základní problém je definován takto:
K dispozici jsou dva kbelíky (předem daných obecně rozdílných objemů), vodovodní kohoutek a kanál. Na počátku jsou oba kbelíky prázdné. Vaším úkolem je docílit toho, aby v jednom kbelíku byla voda o předem stanoveném objemu, přičemž můžete pouze nalít plný kbelík z kohoutku, vylít celý obsah kbelíku do kanálu a přelít jeden kbelík do druhého tak, aby druhý kbelík nepřetekl. Problém lze zobecnit tím, že připustíme užití většího počtu kbelíků, aby na počátku řešení byla v kbelících nějaká voda, a že předepíšeme koncový objem vody v každém kbelíku.

Zkušební instance
-----------------
Zkušební instance jsou popsány v textovém souboru. Každý řádek popisuje jednu instanci. Skládá se z čísel a oddělujících mezer. Je zakončen znakem nový řádel (LF).
Obsahuje jednoznačné ID instance, počet kbelíků n, kapacity kbelíků Vi, počáteční obsahy Si a koncové obsahy Ti:
ID  počet  kapacity  počáteční obsahy  koncové obsahy
    n      V1 V2 Vn  S1 S2 Sn          T1 T2 Tn
