create table public.tbl_coinWatchlist (
    id serial primary key,
    idSymbolCoin varchar(25) NOT NULL,
    idWatchlist int NOT NULL,
    foreign key(idWatchlist) references tbl_watchlist(idWatchlist)
)