# Caliban
Trabajo Practico Parcial Nº1 - Caliban - Deteccion de Mutantes

## Magneto
![alt text](https://github.com/matiasman1/Caliban/blob/main/Max_Eisenhardt_(Earth-616)_from_Uncanny_X-Men_Vol_4_19_003.png?raw=true)

https://marvel.fandom.com/wiki/Max_Eisenhardt_(Earth-616)

## Caliban
![alt text](https://github.com/matiasman1/Caliban/blob/main/Caliban_(Earth-616)_from_NYX_Vol_2_3_002.jpg?raw=true)

https://marvel.fandom.com/wiki/Caliban_(Earth-616)

Magneto ha enlistado la ayuda de Caliban, de su Hermandad de Mutantes Malignos, para detectar mutantes como lo hace el Profesor Xavier con CEREBRO.
Caliban posee la habilidad de, con un sexto sentido, leer el ADN de una persona y determinar si es mutante o humano, y cual es su poder.

Magneto para detectar si una persona es mutante o humana, debe consultar a Caliban con el Client URL 

`curl -X -i POST https:/caliban.onrender.com/mutant/ -H "Content-Type: application/json" -d '{"dna":[`__ADN de la persona__`]}'`

Consecuentemente, Caliban le devolverá, ya sea un codigo `403 Forbidden "Not a Mutant"` si el ADN es humano, o un codigo `200 OK "Mutant Detected"` si el ADN es Mutante

Magneto luego de consultar a Caliban sobre varias personas, puede consultar a Caliban con el Client URL

`curl -X -i GET https:/caliban.onrender.com/stats`

cuantos humanos y mutantes fueron detectados. De esta manera, Caliban le devolvera un JSON de formato `{"count_human_dna":`__Cantidad de humanos__ `,"count_mutant_dna":`__Cantidad de Mutantes__`,"ratio":`__Proporción Mutante/Humano__`}`

Magneto está muy feliz con el servicio de Caliban, y confia que puede realizarle muchas consultas por segundo, viendo que el poder de Caliban es eficiente.

El diagrama de secuencia de los datos que publica y solicita Magneto a Caliban esta en el archivo [Diagrama de Secuencia y Arquitectura del Sistema](https://github.com/matiasman1/Caliban/blob/0efe760720b7da162bc3f1f0103e395a6dec6029/Diagrama%20de%20Secuencia%20y%20Arquitectura%20del%20Sistema.pdf)
