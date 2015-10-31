alter table Privilegio drop foreign key FK_d5ubalh1hff2oo0sgk4j798r4
alter table Privilegio drop foreign key FK_i2i6nwy7jq27xqy3ey56xj9qf
alter table embalagem drop foreign key FK_nq9qru0rxfg5vl7rryqw7je0
alter table embalagem_codigo_barras drop foreign key FK_bkvpso0i9m0petlqh0unl4eoe
alter table produto drop foreign key FK_cu72orus7wbxu7y54ncjwdr0m


drop table if exists embalagem_codigo_barras
drop table if exists embalagem
drop table if exists produto
drop table if exists fabricante
drop table if exists Funcionario
drop table if exists Privilegio
drop table if exists Usuario