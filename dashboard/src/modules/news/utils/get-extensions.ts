import { Extensions } from "@tiptap/core";
import { StarterKit } from "@tiptap/starter-kit";
import Heading from "@tiptap/extension-heading";
import { mergeAttributes } from "@tiptap/react";
import Bold from "@tiptap/extension-bold";
import { Italic } from "@tiptap/extension-italic";
import { Strike } from "@tiptap/extension-strike";
import { Underline } from "@tiptap/extension-underline";

export const getExtensions = () => {
  return [
    StarterKit,
    Heading.configure({
      levels: [1, 2, 3],
    }).extend({
      renderHTML({ node, HTMLAttributes }) {
        const level = node.attrs.level as 1 | 2 | 3;

        let fontSize = "font-size: ";

        switch (level) {
          case 1:
            fontSize = fontSize + "28px";
            break;
          case 2:
            fontSize = fontSize + "24px";
            break;
          case 3:
            fontSize = fontSize + "20px";
            break;
        }

        fontSize = `${fontSize};`;

        return [
          `h${level}`,
          mergeAttributes(HTMLAttributes, {
            style: `${fontSize} font-family: inherit;`,
          }),
          0,
        ];
      },
    }),
    Bold,
    Italic,
    Strike,
    Underline,
  ] as Extensions;
};
