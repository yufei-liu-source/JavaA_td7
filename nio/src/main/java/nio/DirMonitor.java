package nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.NonReadableChannelException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class DirMonitor {
	
	private Path path;

	public DirMonitor(Path path) throws IOException {
		this.path = path;
		if(!Files.isReadable(path)){
			throw new NonReadableChannelException();
		}else if(!Files.isDirectory(path)) {
			throw new FileNotFoundException();
		}
	}
	
	public void showAll() throws IOException{
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(this.path)){
			Iterator<Path> iterator = ds.iterator();
			while (iterator.hasNext()) {
				Path p = iterator.next();
				System.out.println(p);
			}
		}
	}
	
	public long sizeOfFiles() throws IOException {
		long size = 0;
		DirectoryStream<Path> ds = Files.newDirectoryStream(this.path);
		for(Path p : ds) {
			size += Files.size(p);
		}
		return size;
	}
	
	public void mostRecent(File directory) {
		File[] files = directory.listFiles();
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {

				return (int)(o2.lastModified() - o1.lastModified());
			}
		});
		System.out.println(files[0].getName());
	}
	

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

}
