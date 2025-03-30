export enum SosStatusEnum {
  inProgress = "IN_PROGRESS",
  completed = "COMPLETED",
  new = "NEW",
  rejected = "REJECTED",
}

export interface SOSInterface {
  id: number;
  location: string;
  phoneNumber: string;
  createDate: string;
  comment: string,
  address: string,
  status: SosStatusEnum;
}
