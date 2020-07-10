# EMTExchangeBook

EVENTS HANDLING:
За комуникација меѓу модулите е користено event log.

AGGREGATES:
1. userborrowings - ја енкапсулира бизнис логиката поврзана со ентитетите user и borrowings
2. library - ја имплементира бизнис логиката околу работење со приватните библиотеки за секој user, ентитет book

PROJECT STRUCTURE
Структурата на проектот се темели врз структурата предложена во проектот од аудиториски односно, 
за userborrowings модулот
  -userborrowings
      -domain
         -enum
         -event
         -model
         -repository
       -application
         -viewmodel
       -port
       
за library модулот
  -library
      -domain
         -enum
         -model
         -repository
       -application
         -viewmodel
       -integration 
       -port
       
за core модулот
  -core
      -base
      -infrastructure
      -port.rest
      -valueobjects
