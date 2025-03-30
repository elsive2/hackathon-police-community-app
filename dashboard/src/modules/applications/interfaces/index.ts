export interface ApplicationInterface {
  id: number;
  content: string;
  title: string;
  status: string;
  category: string;
  photos: { photoUrl: string }[];
  createDate: string;
  changeDate: string;
  address: string;
}
