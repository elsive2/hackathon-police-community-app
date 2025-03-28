export interface NewsInterface {
  date: string;
  id: number;
  title: string;
  content: string;
  isEditable: boolean;
}

// TODO: fix after mocks remove, remove date field
export interface NewsEditInterface
  extends Omit<NewsInterface, "isEditable" | "id"> {
  id: number | null;
}

export interface NewsErrorsInterface
  extends Pick<NewsInterface, "title" | "content"> {}
