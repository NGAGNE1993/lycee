import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { TypeExam } from 'app/entities/enumerations/type-exam.model';

export interface IExamen {
  id?: number;
  type?: TypeExam;
  autres?: string | null;
  nombrereussi?: number | null;
  nombreEcheque?: number | null;
  nomreAbsent?: number | null;
  lyceesTechniques?: ILyceesTechniques | null;
  directeur?: IDirecteurEtude | null;
}

export class Examen implements IExamen {
  constructor(
    public id?: number,
    public type?: TypeExam,
    public autres?: string | null,
    public nombrereussi?: number | null,
    public nombreEcheque?: number | null,
    public nomreAbsent?: number | null,
    public lyceesTechniques?: ILyceesTechniques | null,
    public directeur?: IDirecteurEtude | null
  ) {}
}

export function getExamenIdentifier(examen: IExamen): number | undefined {
  return examen.id;
}
