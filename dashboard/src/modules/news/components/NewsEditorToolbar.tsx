import { Button } from "@mui/material";
import { useCurrentEditor } from "@tiptap/react";
import { Level } from "@tiptap/extension-heading";

const NewsEditorToolbar = () => {
  const { editor } = useCurrentEditor();

  const onChangeTextLevel = (level: number) => {
    if (!editor) return;

    editor
      .chain()
      .focus()
      .toggleHeading({ level: level as Level })
      .run();
  };

  const onSetBold = () => {
    if (!editor) return;

    editor.chain().focus().toggleBold().run();
  };

  const onSetItalic = () => {
    if (!editor) return;

    editor.chain().focus().toggleItalic().run();
  };

  const onSetStrike = () => {
    if (!editor) return;

    editor.chain().focus().toggleStrike().run();
  };

  const onSetUnderline = () => {
    if (!editor) return;

    editor.chain().focus().toggleUnderline().run();
  };

  if (!editor) {
    return null;
  }

  return (
    <div className={"flex gap-2"}>
      <Button
        onClick={() => onChangeTextLevel(1)}
        variant={
          editor.isActive("heading", { level: 1 }) ? "contained" : "outlined"
        }
      >
        H1
      </Button>
      <Button
        onClick={() => onChangeTextLevel(2)}
        variant={
          editor.isActive("heading", { level: 2 }) ? "contained" : "outlined"
        }
      >
        H2
      </Button>
      <Button
        onClick={() => onChangeTextLevel(3)}
        variant={
          editor.isActive("heading", { level: 3 }) ? "contained" : "outlined"
        }
      >
        H3
      </Button>
      <Button
        onClick={onSetBold}
        variant={editor.isActive("bold") ? "contained" : "outlined"}
      >
        B
      </Button>
      <Button
        onClick={onSetItalic}
        variant={editor.isActive("italic") ? "contained" : "outlined"}
      >
        I
      </Button>
      <Button
        onClick={onSetStrike}
        variant={editor.isActive("strike") ? "contained" : "outlined"}
      >
        S
      </Button>
      <Button
        onClick={onSetUnderline}
        variant={editor.isActive("underline") ? "contained" : "outlined"}
      >
        U
      </Button>
    </div>
  );
};

export default NewsEditorToolbar;
