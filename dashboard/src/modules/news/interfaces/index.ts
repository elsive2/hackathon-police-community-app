export interface NewsInterface {
  changeDate: string | null;
  creationDate: string;
  id: number;
  title: string;
  content: string;
  editable: boolean;
  author: NewsAuthorInterface;
}

export interface NewsCreateInterface
  extends Pick<NewsInterface, "title" | "content" | "editable"> {}

export interface NewsEditInterface
  extends Pick<NewsInterface, "title" | "content" | "editable"> {}

export interface NewsErrorsInterface
  extends Pick<NewsInterface, "title" | "content"> {}

export interface NewsAuthorInterface {
  id: number;
  phoneNumber: string;
  role: any; // TODO: fix later
}
