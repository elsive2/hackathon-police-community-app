export enum SosStatusEnum {
  new,
  inProgress,
  completed,
}

export interface SOSInterface {
  id: number;
  location: string;
  phone: string;
  date: string;
  status: SosStatusEnum;
}
