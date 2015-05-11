Secret Santa Programming Challenge

Part One

The application allows for entering each participant name
Names could be added or removed from the list
Once the list is finalized the SecretSanta assignment could be commenced.
Recipients for each secret santa could be retrieved through UI

Program will generate dump file for SecretSanta assignments upon exit.

Part Two

Application will save the Secret Santa disposition for each term
and will use most recent one to populate the participant list
and the next most recent to add constraints on the selection.

This way the requirement that one cna have the same Secret Santa 
only once every three years could be satisfied.

The constraint mechanism is generic. names are added to the list 
and are taken into account when the shuffle is performed.

Note: if the constraint is once every 3 years for the same santa, 
the solution is always possible if number of participants is larger than 3.

Adding arbitrary constraints will create situations when the solution 
is not possible. But that is for Part Three.
  