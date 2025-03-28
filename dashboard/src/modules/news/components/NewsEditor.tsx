import { TextField } from "@mui/material";
import { EditorProvider } from "@tiptap/react";
import NewsEditorToolbar from "@/modules/news/components/NewsEditorToolbar";
import { getExtensions } from "@/modules/news/utils/get-extensions";

interface NewsEditorProps {
  title: string;
  onChangeTitle: (title: string) => void;
  content: string;
  onChangeContent: (content: string) => void;
}

const NewsEditor = ({
  onChangeTitle,
  title,
  content,
  onChangeContent,
}: NewsEditorProps) => {
  return (
    <>
      <TextField
        label="Заголовок"
        variant="outlined"
        value={title}
        onChange={(e) => onChangeTitle(e.target.value)}
        required={true}
      />
      <EditorProvider
        slotBefore={<NewsEditorToolbar />}
        extensions={getExtensions()}
        content={content}
        onUpdate={({ editor }) =>
          onChangeContent(editor.isEmpty ? "" : editor.getHTML())
        }
        editorProps={{
          attributes: {
            class:
              "bordered border-1 border-[#bdbdbd] rounded-sm p-4 min-h-[300px]",
          },
        }}
      />
    </>
  );
};

export default NewsEditor;
