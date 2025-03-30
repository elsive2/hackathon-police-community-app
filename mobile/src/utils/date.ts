import { format, parseISO } from "date-fns";
import { ru } from "date-fns/locale";

export const formatDate = (value: string) => {
  const date = parseISO(value);

  return format(date, "dd.MM.yyyy HH:mm", { locale: ru });
};
