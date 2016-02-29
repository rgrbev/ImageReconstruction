package netcetera.finki.app.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import netcetera.finki.app.model.Image;
import netcetera.finki.app.model.Keypoints;
import netcetera.finki.app.repository.ImageRepository;
import netcetera.finki.app.repository.KeypointsRepository;
import netcetera.finki.app.service.KeypointsService;

@Service
public class KeypointsServiceImpl implements KeypointsService {

	@Autowired
	ImageRepository imageRep;
	
	@Autowired
	KeypointsRepository keypointsRep;
	
	@Override
	public void readAndSaveResults(String path, long idImage) {
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder();
			
			String koen = br.readLine();
			String vectorSize = br.readLine();
			String vectorsNum = br.readLine();
			
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine + "\n");
				
			}
			String vectors = sb.toString();
			Image i = imageRep.findOne(idImage);
			
			Keypoints kp = new Keypoints(vectors, vectorsNum, vectorSize, i);
			keypointsRep.save(kp);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Keypoints getKeypointsById(long idImage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Keypoints> getAllKeypointsForWorkspaceJob(long idWorkspaceJob) {
		// TODO Auto-generated method stub
		return null;
	}

}
