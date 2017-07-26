%  Program to solve an N-queens problem

% safe(N,Queens) := Queens is a placement that solves the N-queens problem,
% represented as a permutatiion of the list of numbers [1..N].
queens(N,Qs) :- range(1,N,Ns), queens(Ns,[],Qs).

queens(UnplacedQs,SafeQs,Qs) :-
    select(Q,UnplacedQs,UnplacedQs1),
    not(attack(Q,SafeQs)),
    queens(UnplacedQs1,[Q|SafeQs],Qs).
queens([],Qs,Qs).

range(N,M,[N|Xs]) :- N < M, N1 is N + 1, range(N1,M,Xs).
range(N,N,[N]).

% safe(Qs) := the configuration of Qs is safe.
safe([Q|Qs]) :- safe(Qs), not(attack(Q,Qs)).
safe([]).

attack(X,Xs) :- attack(X,1,Xs).
attack(X,N,[Y|Ys]) :- X is Y + N; X is Y - N.
attack(X,N,[Y|Ys]) :- N1 is N + 1, attack(X,N1,Ys).
