import { Skeleton } from "@mui/material";

const NewsEditorSkeleton = () => {
  return (
    <>
      <Skeleton
        className={"rounded-sm"}
        variant="rectangular"
        width={"100%"}
        height={60}
        animation="wave"
      />
      <Skeleton
        className={"rounded-sm"}
        variant="rectangular"
        width={"100%"}
        height={300}
        animation="wave"
      />
    </>
  );
};

export default NewsEditorSkeleton;
